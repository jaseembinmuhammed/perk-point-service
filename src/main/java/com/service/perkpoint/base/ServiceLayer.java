package com.service.perkpoint.base;

import java.util.List;

public interface ServiceLayer<T> {

	T getById(Long id);

	List<T> getByIds(List<Long> ids);
}
