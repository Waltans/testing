package com.urfu.testing.application.serviceImpl;

import com.urfu.testing.application.services.EducationProgramService;
import com.urfu.testing.domain.Module;
import com.urfu.testing.domain.*;
import com.urfu.testing.extern.repository.EducationProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationProgramServiceImpl implements EducationProgramService {

    private final EducationProgramRepository educationProgramRepository;


    @Override
    public Optional<EducationProgram> findById(UUID id) {
        return this.educationProgramRepository.findById(id);
    }

    @Override
    public EducationProgram save(EducationProgram educationProgram) {
        return this.educationProgramRepository.save(educationProgram);
    }

    @Override
    public void delete(UUID uuid) {
        this.educationProgramRepository.findById(uuid)
                .ifPresent(this.educationProgramRepository::delete);
    }


    @Override
    public void update(UUID uuid, String title, String cypher,
                       Level level, Standard standard,
                       Institute institute, Head head,
                       Date accreditationTime, Module module) {

        this.educationProgramRepository.findById(uuid).ifPresent(educationProgram -> {
            if (title != null) educationProgram.setTitle(title);
            if (cypher != null) educationProgram.setCypher(cypher);
            if (level != null) educationProgram.setLevel(level);
            if (standard != null) educationProgram.setStandard(standard);
            if (institute != null) educationProgram.setInstitute(institute);
            if (head != null) educationProgram.setHead(head);
            if (accreditationTime != null) educationProgram.setAccreditationTime(accreditationTime);
            if (module != null) educationProgram.setModule(module);
            educationProgramRepository.save(educationProgram);
        });

    }

    public List<EducationProgram> findAll() {
        return this.educationProgramRepository.findAll();
    }
}
