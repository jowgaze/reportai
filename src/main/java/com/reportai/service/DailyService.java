package com.reportai.service;

import com.reportai.dto.daily.DailyMapper;
import com.reportai.dto.daily.DailyResponseDto;
import com.reportai.entity.Daily;
import com.reportai.entity.Student;
import com.reportai.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyService {
    private final DailyRepository repository;

    public Daily create(String content, Student student){
        Daily daily = new Daily(student, content);
        return repository.save(daily);
    }

    @Transactional(readOnly = true)
    public List<DailyResponseDto> findAllByStudentId(Long id){
        return repository.findByStudent_Id(id)
                .stream().
                map(DailyMapper::toDto)
                .toList();
    }
}
