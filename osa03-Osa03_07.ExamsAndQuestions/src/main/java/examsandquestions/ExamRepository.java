/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsandquestions;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author woohoo
 */
public interface ExamRepository extends JpaRepository<Exam, Long>{
    
}
