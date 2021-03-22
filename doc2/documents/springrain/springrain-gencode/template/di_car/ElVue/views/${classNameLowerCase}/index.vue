<#assign className = table.className>
<#assign tableName = table.tableAlias>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign targetpackage = targetpackage>

<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.data.mintitle" placeholder="标题" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-date-picker v-model="listQuery.data.startDate" type="datetime" class="filter-item" style="width: 200px;" placeholder="开始时间" />

           <el-date-picker v-model="listQuery.data.endDate" type="datetime" placeholder="结束时间" class="filter-item" style="width: 200px;" />

      <el-select v-model="listQuery.data.status" placeholder="状态" clearable class="filter-item" style="width: 130px">
        <el-option v-for="(item,index) in statusMap" :key="index" :label="item" :value="index" />
      </el-select>

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getList">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
<#list table.columns as column>
       <#if !column.pk  && column.columnNameFirstLower?index_of("bak") != 0>

                  <#if column.isDateTimeColumn>

                    <el-table-column width="180px" align="center" label="${column.columnAlias}">
                          <template slot-scope="{row}">
                            <span>{{ row.${column.columnNameFirstLower} | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
                          </template>
                        </el-table-column>

                   <#elseif column.javaType == 'java.lang.Boolean'>


                   <#elseif column.isNumberColumn>


                    <#else>
                             <el-table-column min-width="300px" label="${column.columnAlias}">
                                <template slot-scope="{row}">
                                  <span>{{ row.${column.columnNameFirstLower} }}</span>
                                </template>
                              </el-table-column>

                               </#if>
              </#if>
          </#list>

      <el-table-column align="center" label="Actions" width="120">
        <template slot-scope="{row}">

          <el-button type="text" size="small" icon="el-icon-edit" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button type="text" size="small" icon="el-icon-delete" @click="handleDel(row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
     <el-form ref="dataForm" :rules="rules" :model="form">
    	<#list table.columns as column>
               <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                          <#if column.isDateTimeColumn>

                          <el-form-item label-width="120px" label="${column.columnAlias}:" class="postInfo-container-item" prop="${column.columnNameFirstLower}">
                                  <el-date-picker v-model="form.${column.columnNameFirstLower}" type="datetime" placeholder="请选择${column.columnAlias}" />
                                </el-form-item>


                           <#elseif column.javaType == 'java.lang.Boolean'>
                              <el-form-item label="${column.columnAlias}">
                                <el-switch v-model="form.${column.columnNameFirstLower}"></el-switch>
                              </el-form-item>

                           <#elseif column.isNumberColumn>

                    <el-form-item label="${column.columnAlias}" :label-width="formLabelWidth" prop="${column.columnNameFirstLower}">
                          <el-input v-model="form.${column.columnNameFirstLower}" type="number" autocomplete="请输入${column.columnAlias}" />
                        </el-form-item>
                            <#else>
                              <el-form-item label="${column.columnAlias}" :label-width="formLabelWidth" prop="${column.columnNameFirstLower}">
                                      <el-input v-model="form.${column.columnNameFirstLower}" autocomplete="${column.columnNameFirstLower}" />
                                    </el-form-item>
                                     </#if>
                      </#if>
                  </#list>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleReset">重置</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?create():update()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { get${className}s, create${className}, update${className}, del${className} } from '@/api/${classNameLower}'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive

import { parseTime } from '@/utils'
// import { parseTime } from '@/utils'
export default {
  name: '${className}List',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = [
        '已提交',
        '未审核',
        '审核通过'
      ]
      return statusMap[status]
    }
  },
  data() {
    return {
      statusMap: [
        '已提交',
        '未审核',
        '审核通过'
      ],
      list: [],
      form: {

      <#list table.columns as column>
             <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                        <#if column.isDateTimeColumn>



                         <#elseif column.javaType == 'java.lang.Boolean'>


                         <#elseif column.isNumberColumn>

                            ${column.columnNameFirstLower}: 0,
                          <#else>
                                 ${column.columnNameFirstLower}: '',
                                  </#if>
                    </#if>
                </#list>



      },
      rules: {

            <#list table.columns as column>
                   <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                              <#if column.isDateTimeColumn>


                               <#elseif column.javaType == 'java.lang.Boolean'>


                               <#elseif column.isNumberColumn>

                                <#else>
                                       ${column.columnNameFirstLower}: [{ required: true, message: '${column.columnAlias}是必填项', trigger: 'change' }],
                                        </#if>
                          </#if>
                      </#list>
      }, textMap: {
        update: '编辑',
        create: '添加'
      }, dialogStatus: '',
      formLabelWidth: '120px',
      listLoading: true,
      dialogTableVisible: false,
      dialogFormVisible: false,
      downloadLoading: false,
      listQuery: {
        pageNo: 1,
        pageSize: 20,
        data: {}
      }
    }
  },
  created() {
    this.getList()
  },
   computed: {
      total: vue => {
        if (vue.list) {
          return vue.list.length
        } else {
          return 0
        }
      }
    },
  methods: {
    getList() {
      this.listLoading = true
      get${className}s(this.listQuery).then(response => {
        console.log(response)

             if (response.statusCode === 0) {
                  // response || { pageNO: 1, pageSize: 1, data: [] }
                  this.list = response.result

                  this.listLoading = false
                }
      })
    },
    handleReset() {
      this.form = {

       <#list table.columns as column>
                         <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                                    <#if column.isDateTimeColumn>
           ${column.columnNameFirstLower}: this.$moment(),

                                     <#elseif column.javaType == 'java.lang.Boolean'>


                                     <#elseif column.isNumberColumn>

                                      <#else>
                                             ${column.columnNameFirstLower}: '',
                                              </#if>
                                </#if>
                            </#list>


      }
    },
    create() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
        //  this.form.createDate = this.$moment(this.form.createDate).format('YYYY-MM-DD HH:mm:ss')

          create${className}({ ...this.form, createDate: this.$moment(this.form.createDate).format('YYYY-MM-DD HH:mm:ss') }).then((res) => {

            if (res.statusCode === 0) {
              this.list && this.list.unshift(res.result)
              this.dialogFormVisible = false
              this.$notify({
                title: '添加成功',
                message: res.message,
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '添加失败',
                message: res.message,
                type: 'error',
                duration: 2000
              })
            }
          })
        }
      })
    },
    handleCreate() {
      this.dialogFormVisible = true
      this.handleReset()
      this.dialogStatus = 'create'
    },

    handleUpdate(row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true

      this.form = Object.assign(this.form, row)

      this.form.createDate = this.$moment(this.form.createDate)
    },
    update() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          update${className}({ ...this.form, createDate: this.$moment(this.form.createDate).format('YYYY-MM-DD HH:mm:ss') }).then((res) => {
            if (res.statusCode === 0) {
              for (const v of this.list) {
                if (v.id === res.result.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, res.result)
                  break
                }
              }

              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '编辑成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '编辑失败',
                message: res.message,
                type: 'error',
                duration: 2000
              })
            }
          })
        }
      })
    },
    handleDel(id) {
      del${className}(id).then(res => {
        if (res.statusCode === 0) {
          this.list && this.list.splice(this.list.findIndex(v => v.id === id), 1)
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
        } else {
          this.$notify({
            title: '删除失败',
            message: res.message,
            type: 'error',
            duration: 2000
          })
        }
      })
    },

    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
       const tHeader = [
         <#list table.columns as column>
                                <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                                                    '${column.columnAlias}',
                                       </#if>
                                   </#list>
       ]
       const filterVal = [
              <#list table.columns as column>
                                       <#if !column.pk  && column.columnNameFirstLower?index_of("bak") == -1>

                                                           '${column.columnNameFirstLower}',
                                              </#if>
                                          </#list>
       ]



        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },

  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
