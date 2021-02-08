package com.tesda8.region8.quality.service.mapper;

import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.FeedbackRequestDto;
import com.tesda8.region8.quality.model.entities.Feedback;
import com.tesda8.region8.quality.model.entities.FeedbackRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    Feedback feedbackToEntity(FeedbackDto feedbackDto);
    FeedbackDto feedbackToDto(Feedback feedback);

    FeedbackRequest feedbackRequestToEntity(FeedbackRequestDto feedbackRequestDto);
    FeedbackRequestDto feedbackRequestToDto(FeedbackRequest feedbackRequest);
}
