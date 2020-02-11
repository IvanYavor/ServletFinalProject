package com.company.dao;

import com.company.model.entity.Speciality;

public interface SpecialityDao extends GenericDao<Speciality> {
    Speciality findByName(String specialityName);
}
