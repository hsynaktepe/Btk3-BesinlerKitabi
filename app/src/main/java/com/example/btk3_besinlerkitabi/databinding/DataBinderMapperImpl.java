package com.example.btk3_besinlerkitabi.databinding;

import androidx.databinding.MergedDataBinderMapper;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.example.btk3_besinlerkitabi.DataBinderMapperImpl());
  }
}
