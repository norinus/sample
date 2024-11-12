package com.lab.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.board.service.board.BoardService;
import com.lab.board.service.dto.board.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/create")
    public String create() {
        return "boards/create";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        BoardDTO board = boardService.read(id);
        model.addAttribute("board", board);
        return "boards/read";
    }

    @GetMapping("/list")
    public String getAll(Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, BoardDTO board) {
        model.addAttribute("board", board);
        model.addAttribute("boards", boardService.list(board, pageable));
        return "boards/list";
    }

    @PostMapping
    @ResponseBody
    public Map<String, String> create(BoardDTO board, Model model) {
        boardService.create(board);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "게시글 등록에 성공 하였 습니다.");
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, String> delete(@PathVariable Long id) {

        boardService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "게시글 삭제에 성공 하였 습니다.");
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, String> update(@PathVariable Long id, BoardDTO board, Model model) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "게시글 수정에 성공 하였 습니다.");
            response.put("board", objectMapper.writeValueAsString(boardService.update(id, board)));
            return response;
        } catch (JsonProcessingException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "게시글 포맷 수정에 실패 했습 니다.");
            return response;
        }
    }
}
