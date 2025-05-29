import http from '../util/http.js';
 
 const API={
    getUsers:()=>{
        return http({url:'http://localhost:8080/sysuser/list',method:'get'})
    },
   //新增数据
    addUsers:(data)=>{
        return http({url:'http://localhost:8080/sysuser/add',method:'post',data:data})
    },
 deleteUsers:(id)=>{
  return http({
  url:'http://localhost:8080/sysuser/delete/'+id,
    method:'delete'
  })
},
//修改数据
    updateUsers:(data)=>{
        return http({url:'http://localhost:8080/sysuser/edit',method:'put',data:data})
    },

    get:(url,params)=>{return http({url:url,method:'get',params:params})},
    del:(url,id)=>{
        url=url+'/'+id;//url    /api/satff
        return http({url:url,method:'delete'})}
    ,postForm:function(url,params){console.log(params);return http({url:url,method:'post',params:params})},
    postJson:function(url,data){return http({url:url,method:'post',data:data})},
    putForm:function(url,params){return http({url:url,method:'put',params:params})},
    putJson:function(url,data){return http({url:url,method:'put',data:data})}
};
export default API;