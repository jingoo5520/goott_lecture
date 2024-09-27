package com.miniproject.persistence;

import com.miniproject.domain.PointLogDTO;

public interface PointLogDAO {
	int insertPointLog(PointLogDTO pointLogDTO) throws Exception;
}
