<#assign className = table.className>
<#assign tableName = table.tableAlias>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign targetpackage = targetpackage>

<template>
  <a-modal
    title="新建${tableName}"
    :width="640"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item>
          <a-input type="hidden" v-decorator="[ 'id', {rules: []} ]" />
        </a-form-item>

        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select
            v-decorator="['status', { initialValue: 0, rules: [{required: true}]}]"
            style="width: 100%"
          >
            <a-select-option value="0">关闭</a-select-option>
            <a-select-option value="1">运行中</a-select-option>
            <a-select-option value="2">已上线</a-select-option>

            <a-select-option value="3">异常</a-select-option>
          </a-select>
        </a-form-item>
        	<#list table.columns as column>
        				<#if !column.pk>
        <#if column.isDateTimeColumn>
                <a-form-item label="${column.columnAlias}" :labelCol="labelCol" :wrapperCol="wrapperCol">
                  <a-date-picker
                    format="YYYY-MM-DD HH:mm:ss"
                    :disabledDate="disabledDate"
                    :disabledTime="disabledDateTime"
                    :showTime="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
                    v-decorator="['${column.columnNameFirstLower}', {rules: [{required: true, message: '请输入创建时间'}]}]"
                  />
                </a-form-item>
        <#elseif column.javaType == 'java.lang.Boolean'>
          <a-form-item label="${column.columnAlias}" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-switch defaultChecked @change='onChange'  v-decorator="['${column.columnNameFirstLower}', {rules: [{required: true, message: '请输入${column.columnAlias}'}]}]"/>
            </a-form-item>
            <#elseif column.isNumberColumn>
             <a-form-item label="${column.columnAlias}" :labelCol="labelCol" :wrapperCol="wrapperCol">
                      <a-input-number
                        :min="1"
                        v-decorator="['${column.columnNameFirstLower}', {rules: [{required: true, message: '请输入${column.columnAlias}'}]}]"
                      />
                    </a-form-item>
                    <#else>
                        <a-form-item label="${column.columnAlias}" :labelCol="labelCol" :wrapperCol="wrapperCol">
                          <a-input
                            v-decorator="['${column.columnNameFirstLower}', {rules: [{required: true, min: 5, message: '请输入${column.columnAlias}'}]}]"
                          />
                        </a-form-item>
                    </#if>
 </#if>
 </#list>
        <a-form-item label="富文本" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <quill-editor
            ref="myTextEditor"
            v-model="content"
            :config="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @ready="onEditorReady($event)"
          ></quill-editor>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import moment from 'moment'
import pick from 'lodash.pick'
import { addorupdate${className} } from '@/api/${classNameLower}'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import { quillEditor } from 'vue-quill-editor'
export default {
  components: {
    quillEditor
  },
  props: {
    record: {
      type: [Object, String],
      default: ''
    }
  },
  data() {
    return {
      content: '<h2>I am Example</h2>',
      editorOption: {
        // something config
      },
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 13 }
      },
      visible: false,
      confirmLoading: false,

      form: this.$form.createForm(this)
    }
  },
  methods: {
    moment,
    add() {
      this.form.resetFields()
      this.visible = true
    },
    edit(record) {
      this.visible = true
      const {
        form: { setFieldsValue }
      } = this
      this.$nextTick(() => {

      <#list table.columns as column>
              				<#if !column.pk>
              <#if column.isDateTimeColumn>

        record.${column.columnNameFirstLower} = moment(record.${column.columnNameFirstLower})


                    </#if>
 </#if>
 </#list>

        setFieldsValue(pick(record, [
        <#list table.columns as column>
                      				<#if !column.pk>
        '${column.columnNameFirstLower}',
 </#if>
 </#list>
       'id' ])) // 挑选出其中几项做修改

        // setFieldsValue(record)
      })
    },

    range(start, end) {
      const result = []
      for (let i = start; i < end; i++) {
        result.push(i)
      }
      return result
    },
     <#list table.columns as column>
                  				<#if !column.pk>
                  <#if column.isDateTimeColumn>

    disabledDate(current) {
      // Can not select days before today and today
      return current && current < moment().endOf('day')
    },

    disabledDateTime() {
      return {
        disabledHours: () => this.range(0, 24).splice(4, 20),
        disabledMinutes: () => this.range(30, 60),
        disabledSeconds: () => [55, 56]
      }
    },


                    </#if>
 </#if>
 </#list>
    handleSubmit() {
      const {
        form: { validateFields }
      } = this
      this.confirmLoading = true
      validateFields((errors, values) => {
        if (!errors) {

         <#list table.columns as column>
                      				<#if !column.pk>
                      <#if column.isDateTimeColumn>

                values.${column.columnNameFirstLower} = values.${column.columnNameFirstLower}.format('YYYY-MM-DD HH:mm:ss')


                            </#if>
         </#if>
         </#list>



          addorupdate${className}(values)
            .then(res => {
              this.visible = false

              this.$emit('ok', res)
            })
            .catch(err => {
              this.$notification['error']({
                message: '错误',
                // description: ((err.response || {} || err).data || {}).message || err || '请求出现错误，请稍后再试',
                description: (err.response || {} || err).data || err || '请求出现错误，请稍后再试',
                duration: 4
              })
            })
            .finally(() => {
              this.confirmLoading = false
            })
        } else {
          this.confirmLoading = false
        }
      })
    },
    handleCancel() {
      this.visible = false
    }
  }
}
</script>
