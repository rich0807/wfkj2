package com.rq.rqwfkj.minsu.controller;

import com.rq.rqwfkj.minsu.entity.Sysuser;
import com.rq.rqwfkj.minsu.service.ISysuserService;
import com.rq.rqwfkj.minsu.util.EasyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RQ
 * @since 2025-05-26
 */
@RestController
@RequestMapping("/sysuser")
public class SysuserController {
    @Autowired
ISysuserService sysuserService;

    @RequestMapping("/list")
    public EasyResult getSysuser(){
      List<Sysuser> list = sysuserService.list();
      return EasyResult.success(list);
    }
}
