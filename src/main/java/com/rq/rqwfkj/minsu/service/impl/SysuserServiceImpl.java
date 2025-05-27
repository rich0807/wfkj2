package com.rq.rqwfkj.minsu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rq.rqwfkj.minsu.entity.Sysuser;
import com.rq.rqwfkj.minsu.mapper.SysuserMapper;
import com.rq.rqwfkj.minsu.service.ISysuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RQ
 * @since 2025-05-26
 */
@Service
public class SysuserServiceImpl extends ServiceImpl<SysuserMapper, Sysuser> implements ISysuserService {

    @Override
    public Sysuser login(Sysuser sysuser) {
        //校验数据库中是否有对应的账号
        QueryWrapper wrapper=new QueryWrapper<Sysuser>();
        wrapper.eq("username",sysuser.getUsername());
        Sysuser result=this.getOne(wrapper);
        if(result!=null) {
            //如果有，对比密码是否一样
            if(result.getUsername().equals(sysuser.getUsername())){
                //如果密码一样登陆成功
                return sysuser;
            }
        }
        return null;
    }
}
