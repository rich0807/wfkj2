<template>
  <div class="mb-4">
  <el-button type="primary" @click="handlerAdd">新增</el-button>
  </div>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="id" label="编号" width="150" />
    <el-table-column prop="username" label="用户名" width="120" />
    <el-table-column prop="nickname" label="昵称" width="120" />
    <el-table-column prop="phone" label="联系方式" width="120" />
    <el-table-column prop="gender" label="性别" width="600" />
    <el-table-column fixed="right" label="Operations" min-width="120">
      <template   #default="{ row }">
        <el-button link type="primary" size="small" @click="handleClick(row)">
          编辑
        </el-button>
        <el-button link type="danger" size="small" @click="showDeleteDialog(row.id)">删除</el-button>
      </template>
  
    </el-table-column>
  </el-table>
  <el-dialog v-model="deleteVisible" title="删除确认" width="400px">
  <span>确定要删除这条数据吗？</span>
  <template #footer >
    <el-button @click="deleteVisible = false">取消</el-button>
    <el-button type="danger" @click="confirmDelete">确认删除</el-button>
  </template>
</el-dialog>
  <!--新增数据接口-->         
  <el-dialog
      v-model="dialogVisible"
      title="新增数据"
      width="500"      
  >
  
  
     <el-form :model="addData" label-width="auto" style="max-width: 600px">
    <el-form-item label="用户账号">
      <el-input v-model="addData.username" placeholder="请输入用户账号" />
    </el-form-item>
    <el-form-item label="联系方式">
      <el-input v-model="addData.phone" placeholder="请输入联系方式" />
    </el-form-item>
     <el-form-item label="性别">
      <el-radio-group v-model="addData.gender">
        <el-radio value="男">男</el-radio>
        <el-radio value="女">女</el-radio>
      </el-radio-group>
    </el-form-item>
     </el-form>
    
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitAdd">
          提交
        </el-button>
      </div>      
    </template>
  </el-dialog>

 <!-- 只保留一个删除对话框 -->
<el-dialog v-model="deleteVisible" title="删除确认" width="400px">
   <!-- 显示删除数据的id -->

  <span>确定要删除 <span>ID为 {{ deleteId }}</span>的数据吗？</span>
  <template #footer>
    <el-button @click="deleteVisible = false">取消</el-button>
    <el-button type="danger" @click="confirmDelete">确认删除</el-button>
  </template>
</el-dialog>
<!--  编辑对话框 -->
<el-dialog v-model="editVisible" title="编辑数据" width="500">
  <el-form :model="editData" label-width="auto" style="max-width: 600px">
    <el-form-item label="用户账号">
      <el-input v-model="editData.username" />
    </el-form-item>
    <el-form-item label="用户昵称">
      <el-input v-model="editData.nickname" />
    </el-form-item>
    <el-form-item label="联系方式">
      <el-input v-model="editData.phone" />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="editData.gender">
        <el-radio value="男">男</el-radio>
        <el-radio value="女">女</el-radio>
      </el-radio-group>
    </el-form-item>
  </el-form>
  
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="editVisible = false">取消</el-button>
      <el-button type="primary" @click="confirmEdit">提交修改</el-button>
    </div>
  </template>
</el-dialog>



</template>

<script lang="ts" setup>
// 在需要使用的组件中单独导入
import { ElMessage } from 'element-plus'


import {ref,onMounted} from 'vue'
import API from './api/index.js';
//绑定表格数据
const tableData=ref([]);
onMounted(async function() {
const result=await API.getUsers();
//将获取到的数据绑定到表格数据中
tableData.value=result.data;
});



const addData = ref({
  username: '',
 gender:'男',
  phone: ''
});

//新增窗口是否可见
const dialogVisible=ref(false);
//点击新增按钮执行方法
const handlerAdd=function (){
  //清空新增数据
  addData.value.username= ''
  addData.value.phone= ''
  addData.value.gender= '男'
dialogVisible.value=true;
}
//提交新增数据
const submitAdd = async function () {
  //调用新增接口
 const result = await API.addUsers(addData.value);
 //添加返回的数据
  tableData.value.unshift(result.data);
    //关闭新增窗口
    dialogVisible.value = false;
 
};



// 新增状态变量
const deleteId = ref<number>(0)
const deleteVisible = ref(false)

// 显示删除对话框
const showDeleteDialog = (id: number) => {
  deleteId.value = id
  deleteVisible.value = true
}


const confirmDelete = async () => {
  try {
    await API.deleteUsers(deleteId.value)
    tableData.value = tableData.value.filter(item => item.id !== deleteId.value)
    deleteVisible.value = false
    ElMessage.success('删除成功')
  } catch (error) {
    ElMessage.error('删除失败')
  }
}

// 编辑按钮点击事件
// 编辑相关状态
const editVisible = ref(false)
const editData = ref({
  id: 0,
  username: '',
  nickname: '',
  phone: '',
  gender: '男'
})

interface UserData {
  id: number
  username: string
  nickname: string
  phone: string
  gender: '男' | '女'
}

// 编辑按钮点击处理
const handleClick = (row: any) => {
  editData.value = { ...row } // 使用对象展开符复制原始数据
  editVisible.value = true
}

// 提交编辑
const confirmEdit = async () => {
  try {
    await API.updateUsers(editData.value)
    // 更新表格数据
    const index = tableData.value.findIndex(item => item.id === editData.value.id)
    if (index !== -1) {
      tableData.value[index] = { ...editData.value }
    }
    editVisible.value = false
    ElMessage.success('更新成功')
  } catch (error) {
    ElMessage.error('更新失败')
  }
}



</script>       

<style scoped>

</style>


