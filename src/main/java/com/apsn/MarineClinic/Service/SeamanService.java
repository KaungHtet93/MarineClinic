package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Repository.SeamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeamanService {
    @Autowired
    private SeamanRepository seamanRepository;
}
