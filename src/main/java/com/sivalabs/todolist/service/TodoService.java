package com.sivalabs.todolist.service;

import com.sivalabs.todolist.entity.Todo;
import com.sivalabs.todolist.repo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getTodos() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sort);
    }

    public Todo saveTodo(Todo todo) {
        todo.setId(null);
        return todoRepository.save(todo);
    }

    public boolean deleteTodo(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
