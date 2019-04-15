package com.taskManager.vo.response;

/**
 * 通用返回结果:泛型只存在编译阶段，运行阶段不存在泛型，也就是编译完成之后泛型就被具体化了。 泛型对比定义object的好处：1.自动检验，更规范
 * 2.隐式转化，代码更简洁
 * 
 * @author 14145
 *
 * @param <T>
 */
public class CommonResult<T> {

	private String code;

	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
