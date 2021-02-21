## 选项卡  :id=back_side

问题 : 如何在子页面打开新的 Tab 标签页 ? 

回答 : top.layui.tab.addTabOnlyByElem("content",{id:"标识",title:"标题",url:"路径",close:"允许关闭"})

问题 : 如何在顶级页面打开的新的 Tab 标签页 ?

回答 : layui.tab.addTabOnlyByElem("content",{id:"标识",title:"标题",url:"路径",close:"允许关闭"})

问题 : 如何在子页面关闭当前 Tab 标签页 ?

回答 : top.tab.delCurrentTabByElem("content",callback())

问题 : 如何在顶级页面关闭当前 Tab 标签页 ?

回答 : layui.tab.delCurrentTabByElem("content",callback())
