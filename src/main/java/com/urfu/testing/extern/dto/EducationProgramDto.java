package com.urfu.testing.extern.dto;

import com.urfu.testing.domain.Level;
import com.urfu.testing.domain.Standard;

import java.util.Date;
import java.util.UUID;

public record EducationProgramDto(UUID id, String title, String cypher, Level level, UUID institute, UUID head,
                                  Date accreditationTime, Standard standard, UUID module) {
}
