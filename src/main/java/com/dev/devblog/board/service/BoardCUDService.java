package com.dev.devblog.board.service;

import com.dev.devblog.board.dao.BoardRepository;
import com.dev.devblog.board.dao.BoardTagInfoRepository;
import com.dev.devblog.board.domain.Boards;
import com.dev.devblog.board.dto.BoardSaveRequest;
import com.dev.devblog.board.dto.BoardUpdateStatusRequest;
import com.dev.devblog.board.entity.Board;
import com.dev.devblog.category.dao.CategoryRepository;
import com.dev.devblog.category.entity.Category;
import com.dev.devblog.tag.entity.BoardTagInfo;
import com.dev.devblog.tags.dao.TagsRepository;
import com.dev.devblog.tags.entity.Tags;
import com.dev.devblog.user.dao.UserRepository;
import com.dev.devblog.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;



@Service
@RequiredArgsConstructor
@Transactional
public class BoardCUDService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagsRepository tagsRepository;
    private final BoardTagInfoRepository boardTagInfoRepository;

    public void boardSave(BoardSaveRequest request, Long userCode) {
        User user = userRepository.findById(userCode).orElseThrow(
                () -> new NoSuchElementException("게시글 작성 회원을 찾을 수 없습니다.")
        );
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new NoSuchElementException("카테고리를 확인할 수 없습니다.")
        );

        Board board = Boards.of(request, user, category).toCreateEntity();
        boardRepository.save(board);

        List<Tags> list = saveTagList(request.getTagList());
        saveBoardTagInfo(board, list);
    }

    private void saveBoardTagInfo(Board board, List<Tags> tagList) {
        for(Tags tags : tagList) {
            BoardTagInfo tagInfo = BoardTagInfo.toEntity(board.getBoardId(), tags);
            boardTagInfoRepository.save(tagInfo);
        }
    }

    private List<Tags> saveTagList(Map<String, Long> tagList) {
        List<Tags> list = new ArrayList<>();

        for(String key : tagList.keySet()) {
            Tags tags = tagsRepository.findByTagName(key).orElseGet(() -> tagsRepository.save(Tags.toEntity(key)));
            list.add(tags);
        }

        return list;
    }

    public void boardStatusUpdate(BoardUpdateStatusRequest request) {
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new NoSuchElementException("변경할 게시글을 찾을 수 없습니다")
        );

        board.updateStatus(request.getStatus());
    }

    public void boardDelete(List<Long> boardIdArr) {
        for(Long boardId : boardIdArr) {
            boardRepository.findById(boardId).orElseThrow(
                    () -> new NoSuchElementException("삭제할 게시글이 존재하지 않습니다.")
            );

            boardRepository.deleteById(boardId);
        }
    }

    public void boardUpdate(BoardSaveRequest request) {
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new NoSuchElementException("수정할 게시글이 존재하지 않습니다.")
        );
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new NoSuchElementException("수정할 게시글에 카테고리가 존재하지 않습니다.")
        );

        board.updateSubject(request.getSubject());
        board.updateContent(request.getContent());
        board.updateCategoryId(category);
        board.updateDate(LocalDateTime.now());

        List<Tags> list = saveTagList(request.getTagList());
        boardTagInfoRepository.deleteByBoardId(board.getBoardId());

        for(Tags tags : list) {
            BoardTagInfo tagInfo = BoardTagInfo.toEntity(board.getBoardId(), tags);
            boardTagInfoRepository.save(tagInfo);
        }
    }
}
