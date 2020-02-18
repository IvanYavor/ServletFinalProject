package com.company.dao;

import com.company.entity.Speciality;

public interface SpecialityDao extends GenericDao<Speciality> {
    Speciality findByName(String specialityName);
}
