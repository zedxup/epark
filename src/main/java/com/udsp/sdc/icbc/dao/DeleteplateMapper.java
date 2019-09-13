package com.udsp.sdc.icbc.dao;

import org.apache.ibatis.annotations.Param;


public interface DeleteplateMapper {
	int  updateUserPlate(@Param("plate") String plate);
}
