package com.codegym.repository;

import com.codegym.dto.IQuestionContentDto;
import com.codegym.model.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<QuestionContent, Integer> {
    @Query(value = "select qc.* from `question_content` qc join `question_type` qt on qt.id = qc.question_type_id where qc.title like concat('%', :title '%') and qt.name " +
            "like concat('%', :questionType, '%') and qc.flag_delete = false order by qc.status asc, qc.date_create asc  ", nativeQuery = true)
    Page<QuestionContent> showList(@Param("title") String title, @Param("questionType") String questionType, Pageable pageable);

    @Query(value = "select qc.id, " +
            "qc.title as title , " +
            "qc.content as contents," +
            "qc.answer as answer, " +
            "qc.date_create as dateCreate, " +
            "qc.status as status, " +
            "qt.name as name " +
            "from `question_content` qc " +
            "join `question_type` qt " +
            "on qc.question_type_id = qt.id " +
            "where qc.id = :id " +
            "and qc.flag_delete = false ",nativeQuery = true)
    List<IQuestionContentDto> findAll(@Param("id") Long id);
}
