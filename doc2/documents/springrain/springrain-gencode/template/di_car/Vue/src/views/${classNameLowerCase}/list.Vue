
<#assign className = table.className>
<#assign tableName = table.tableAlias>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign targetpackage = targetpackage>
<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="8" :sm="24">
            <a-form-item label="规则编号">
              <a-input v-model="queryParam.id" placeholder />
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24">
            <a-form-item label="使用状态">
              <a-select v-model="queryParam.status" placeholder="请选择" default-value="0">
                <a-select-option value="0">关闭</a-select-option>
                <a-select-option value="1">运行中</a-select-option>
                <a-select-option value="2">已上线</a-select-option>

                <a-select-option value="3">异常</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <template v-if="advanced">
            <a-col :md="8" :sm="24">
              <a-form-item label="标题">
                <a-input v-model="queryParam.mintitle" placeholder />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="调用次数">
                <a-input-number v-model="queryParam.lookcount" style="width: 100%" />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="更新日期">
                <a-date-picker
                  v-model="queryParam.createDate"
                  style="width: 100%"
                  placeholder="请输入更新日期"
                />
              </a-form-item>
            </a-col>
          </template>
          <a-col :md="!advanced && 8 || 24" :sm="24">
            <span
              class="table-page-search-submitButtons"
              :style="advanced && { float: 'right', overflow: 'hidden' } || {} "
            >
              <a-button type="primary" icon="plus" @click="$refs.createModal.add()">新建</a-button>
              <a-button
                style="margin-left: 6px"
                type="dashed"
                @click="tableOption"
              >{{ optionAlertShow && '关闭' || '开启' }}</a-button>
              <a-button
                style="margin-left: 6px"
                type="primary"
                @click="$refs.table.refresh(true)"
              >查询</a-button>
              <a-button style="margin-left: 6px" @click="() => queryParam = {}">重置</a-button>
              <a @click="toggleAdvanced" style="margin-left: 8px">
                {{ advanced ? '收起' : '展开' }}
                <a-icon :type="advanced ? 'up' : 'down'" />
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>

    <div class="table-operator">
      <a-dropdown v-action:edit v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1">
            <a-icon type="delete" />删除
          </a-menu-item>
          <!-- lock | unlock -->
          <a-menu-item key="2">
            <a-icon type="lock" />锁定
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">
          批量操作
          <a-icon type="down" />
        </a-button>
      </a-dropdown>
    </div>

    <s-table
      ref="table"
      size="default"
      rowKey="id"
      :columns="columns"
      :data="loadData"
      :alert="options.alert"
      :rowSelection="options.rowSelection"
      showPagination="auto"
    >
      <span slot="serial" slot-scope="text, record, index">{{ index + 1 }}</span>
      <span slot="status" slot-scope="text">
        <a-badge :status="text | statusTypeFilter" :text="text | statusFilter" />
      </span>
      <span slot="description" slot-scope="text">
        <ellipsis :length="40" tooltip>{{ text }}</ellipsis>
      </span>

      <span slot="action" slot-scope="text, record">
        <template>
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a @click="handleDel(record)">删除</a>
          <a-divider type="vertical" />
        </template>
      </span>
    </s-table>
    <create-form ref="createModal" @ok="handleOk($event)" />
  </a-card>
</template>

<script>
import moment from 'moment'
import { STable, Ellipsis } from '@/components'
import CreateForm from './modules/CreateForm'

import { get${className}s, del${className} } from '@/api/${classNameLowerCase}'

const statusMap = {
  0: {
    status: 'default',
    text: '关闭'
  },
  1: {
    status: 'processing',
    text: '运行中'
  },
  2: {
    status: 'success',
    text: '已上线'
  },
  3: {
    status: 'error',
    text: '异常'
  }
}

export default {
  name: 'List',
  components: {
    STable,
    Ellipsis,
    CreateForm

  },
  data() {
    return {
      mdl: {},
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      // 表头
      columns: [
        {
          title: '#',
          scopedSlots: { customRender: 'serial' }
        },
	<#list table.columns as column>
	    <#if !column.pk>
        {
          title: '${column.columnAlias}',
          dataIndex: '${column.columnNameFirstLower}'
        },
        </#if>
    </#list>
        {
          title: '操作',
          dataIndex: 'action',
          width: '150px',
          scopedSlots: { customRender: 'action' }
        }
      ],
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        return get${className}s(Object.assign(parameter, { data: this.queryParam })).then(res => {
          return res
        })
      },
      selectedRowKeys: [],
      selectedRows: [],

      // custom table alert & rowSelection
      options: {
        alert: {
          show: true,
          clear: () => {
            this.selectedRowKeys = []
          }
        },
        rowSelection: {
          selectedRowKeys: this.selectedRowKeys,
          onChange: this.onSelectChange
        }
      },
      optionAlertShow: true
    }
  },
  filters: {
    statusFilter(type) {
      return statusMap[type].text
    },
    statusTypeFilter(type) {
      return statusMap[type].status
    }
  },
  created() {
    this.tableOption()
  },
  methods: {
    tableOption() {
      if (!this.optionAlertShow) {
        this.options = {
          alert: {
            show: true,
            clear: () => {
              this.selectedRowKeys = []
            }
          },
          rowSelection: {
            selectedRowKeys: this.selectedRowKeys,
            onChange: this.onSelectChange,
            getCheckboxProps: record => ({
              props: {
                disabled: record.no === 'No 2', // Column configuration not to be checked
                name: record.no
              }
            })
          }
        }
        this.optionAlertShow = true
      } else {
        this.options = {
          alert: false,
          rowSelection: null
        }
        this.optionAlertShow = false
      }
    },

    handleEdit(record) {
      console.log(record)
      this.$refs.createModal.edit(record)
    },

    handleOk(record) {
      // 更新修改返回对象，table中有id 则删除 添加，没有id直接添加
      const i = this.$refs.table.localDataSource.findIndex(x => x.id == record.id)
      if (i > -1) {
        this.$refs.table.localDataSource.splice(i, 1, record)
      } else {
        this.$refs.table.localDataSource.unshift(record)
      }

      // this.$refs.table.refresh()
    },
    handleDel(record) {
      del${className}({ id: record.id })
        .then(res => {
          this.$refs.table.localDataSource.pop(record)
          this.$message.info(`删除成功`)
        })
        .catch(res => {
          this.$message.info(res.data)
        })
    },
    onSelectChange(selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    toggleAdvanced() {
      this.advanced = !this.advanced
    },
    resetSearchForm() {
      this.queryParam = {
        date: moment(new Date())
      }
    }
  }
}
</script>
<style>
.ant-card-body {
  padding: 6px 8px !important;
}

.ant-form-item {
  margin-bottom: 4px !important;
}
.table-page-search-submitButtons {
  margin-bottom: 0 !important;
}
</style>
