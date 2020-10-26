package com.tesda8.region8.web.service.impl;

import com.tesda8.region8.web.model.entities.DailyReportInfo;
import com.tesda8.region8.web.repository.DailyReportInfoRepository;
import com.tesda8.region8.web.service.DailyReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DailyReportInfoServiceImpl implements DailyReportInfoService {

    private DailyReportInfoRepository dailyReportInfoRepository;

    @Autowired
    public DailyReportInfoServiceImpl(DailyReportInfoRepository dailyReportInfoRepository) {
        this.dailyReportInfoRepository = dailyReportInfoRepository;
    }

    @Override
    public DailyReportInfo getLatestDailyReportInfo() {
        List<DailyReportInfo> dailyReportInfoList = dailyReportInfoRepository.findAll();
        return dailyReportInfoList
                .stream()
                .max(Comparator.comparing(DailyReportInfo::getId))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void saveDailyReportInfo(DailyReportInfo dailyReportInfo) {
        dailyReportInfoRepository.save(dailyReportInfo);
    }
}
