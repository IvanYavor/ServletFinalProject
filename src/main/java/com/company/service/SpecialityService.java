package com.company.service;

import com.company.dao.DaoFactory;
import com.company.dao.SpecialityDao;
import com.company.entity.Speciality;

import java.util.List;

public class SpecialityService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Speciality> getAllSpeciality() {
        try (SpecialityDao dao = daoFactory.createSpecialityDao()) {
            return dao.findAll();
        }
    }

    public void createSpeciality(Speciality speciality) {
        try (SpecialityDao dao = daoFactory.createSpecialityDao()) {
            dao.create(speciality);
        }
    }


    public Speciality getSpecialityByName(String specialityName) {
        try (SpecialityDao dao = daoFactory.createSpecialityDao()) {
            return dao.findByName(specialityName);
        }
    }
}
