package com.mos.eboot.tools.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.tools.exception.NormalException;
import com.mos.eboot.tools.result.LayPage;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import org.springframework.validation.BindingResult;

import java.util.List;


public class BaseController {

    protected void validError(BindingResult result) {
        if (result.hasErrors()) {
            throw new NormalException(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

    protected ResultModel<String> basicResult(Boolean flag) {
        return flag ? ResultModel.defaultSuccess(null) : ResultModel.defaultError(null);
    }

    protected <T> T  getData(ResultModel<T> resultModel){
        if (ResultStatus.SUCCESS.getCode().equals(resultModel.getCode())){
            return resultModel.getData();
        }
        throw new NormalException(resultModel.getMsg());
    }

    protected <T> LayPage<T> getLayPage(ResultModel<Page<T>> resultModel){
        LayPage<T> layPage = new LayPage<>();
        Page<T> page = resultModel.getData();
        if (page == null){
            return layPage;
        }
        layPage.setCode(Integer.parseInt(resultModel.getCode()));
        layPage.setCount(page.getTotal());
        layPage.setData(page.getRecords());
        layPage.setMsg(resultModel.getMsg());
        return layPage;
    }

    protected <T> LayPage<T> getLayPage4List(ResultModel<List<T>> resultModel){
        LayPage<T> layPage = new LayPage<>();
        List<T> list = resultModel.getData();
        if (list == null){
            return layPage;
        }
        layPage.setCode(Integer.parseInt(resultModel.getCode()));
        layPage.setCount(list.size());
        layPage.setData(list);
        layPage.setMsg(resultModel.getMsg());
        return layPage;
    }
}
