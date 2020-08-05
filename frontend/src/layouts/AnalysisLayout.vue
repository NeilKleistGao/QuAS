<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <h1 class="quas-fill-header">{{title}}——问卷分析</h1>
        <br/>

        <div class="quas-analysis-content">
            <div class="quas-analysis-cell">
                <quas-list title="分析列表" v-model="analyze_title" style="height: 100%" @callback="update"/>
            </div>
            <div class="quas-analysis-cell">
                <div v-if="active_index !== -1" style="margin-top: 20px; margin-left: 20px" :key="update_key">
                    <label style="display: inline-block">名称：</label>
                    <quas-text-box style="display: inline-block" v-model="analyze_items[active_index].title"/>
                    <br/>
                    <br/>
                    <quas-dropdown :editable="false" :contents="analyze_drop_content" v-model="temp_type"/>

                    <br/>
                    <div v-if="analyze_items[active_index].method !== ''">
                        <label>使用题目数据：</label>
                        <quas-check-box-group :editable="false"
                                              :items="questionnaire_item_content"
                                              v-model="temp_labels"/>

                        <button class="quas-submit-button" style="margin-top: 20px" @click="checkAndShow">提交计算</button>
                    </div>

                    <div v-if="test_show">
                        TODO:分析结果
                    </div>

                    <button class="quas-warning-button" style="position: fixed; bottom: 100px">删除分析</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasList from "@/components/QuasWebUI/QuasList";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    import QuasDropdown from "@/components/QuasQuestionnaire/QuasDropdown";
    import QuasCheckBoxGroup from "@/components/QuasQuestionnaire/QuasCheckBoxGroup";
    export default {
        name: "AnalysisLayout",
        components: {QuasCheckBoxGroup, QuasDropdown, QuasTextBox, QuasList, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试", "问卷分析测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154", "#/questionnaire/analysis/3154"],
                title: "关于问卷测试的问卷调查",
                analyze_title: [],
                analyze_items: [{
                    title: "分析1",
                    type: "",
                    method: "",
                    data: {

                    }
                },{
                    title: "分析2",
                    type: "",
                    method: "",
                    data: {

                    }
                },{
                    title: "分析3",
                    type: "",
                    method: "",
                    data: {

                    }
                }],
                active_index: -1,
                update_key: 0,
                analyze_drop_content: {
                labels: ["分析类型：", "分析方法"],
                    items: [{
                        level: 0,
                        label: "数值计算"
                    }, {
                        level: 1,
                        label: "众数"
                    }, {
                        level: 0,
                        label: "关联计算"
                    },{
                        level: 1,
                        label: "卡方检验"
                    },{
                        level: 1,
                        label: "Spearman相关系数"
                    },{
                        level: 1,
                        label: "互信息"
                    },]
                },
                temp_type: {
                    result: ["", ""]
                },
                questionnaire_item_content: {
                    labels: [],
                    min: -1
                },
                questionnaire: [
                    {
                        type: "none",
                        data: {
                            information: "这是一个测试",
                            content: null,
                            next: 0
                        }
                    },
                    {
                        type: "radio",
                        data: {
                            information: "这是一个单选题",
                            content: {
                                labels: ["选项１", "选项2", "选项3"],
                                next: [3, 4, 5]
                            },
                            next: 0
                        }
                    },
                    {
                        type: "check",
                        data: {
                            information: "这是一个多选题",
                            content: {
                                labels: ["选项１", "选项2", "选项3"]
                            },
                            next: -1
                        }
                    },
                    {
                        type: "date",
                        data: {
                            information: "这是一个日期",
                            content: {
                                type: "date"
                            },
                            next: -1
                        }
                    },
                    {
                        type: "text",
                        data: {
                            information: "这是一个填空题",
                            content: {
                            },
                            next: 0
                        }
                    },
                    {
                        type: "rich",
                        data: {
                            information: "这是一个多行文本",
                            content: {

                            },
                            next: 0
                        }
                    },
                    {
                        type: "drop",
                        data: {
                            information: "这是一个下拉选项",
                            content: {
                                labels: ["题目1"],
                                items: [{
                                    level: 0,
                                    label: "选项1"
                                },{
                                    level: 0,
                                    label: "选项2"
                                }]
                            },
                            next: 0
                        }
                    },
                    {
                        type: "sort",
                        data: {
                            information: "这是一个排序题",
                            content: {
                                labels: ["选项1", "选项2", "选项3"]
                            },
                            next: 0
                        }
                    }
                ],
                temp_labels: {
                    result: []
                },
                test_show: false
            }
        },
        beforeMount() {
            for (let item of this.analyze_items) {
                this.analyze_title.push(item.title);
            }

            for (let ques of this.questionnaire) {
                if (ques.type !== "none" && ques.type !== "text" && ques.type !== "rich") {
                    this.questionnaire_item_content.labels.push(ques.data.information);
                }
            }
        },
        methods: {
            update(index) {
                this.active_index = index;
                this.update_key++;
            },
            checkAndShow() {
                if (this.temp_labels.result.length < this.questionnaire_item_content.min) {
                    alert("至少需要选择" + this.questionnaire_item_content.min + "项");
                }
                else {
                    this.test_show = true;
                }
            }
        },
        watch: {
            temp_type: {
                handler(new_value) {
                    this.analyze_items[this.active_index].type = new_value.result[0];
                    this.analyze_items[this.active_index].method = new_value.result[1];

                    if (this.analyze_items[this.active_index].type === "数值计算") {
                        this.questionnaire_item_content.min = 1;
                    }
                    else if (this.analyze_items[this.active_index].type === "关联计算") {
                        this.questionnaire_item_content.min = 2;
                    }

                    this.temp_labels.result = [];
                },
                deep: true
            }
        }
    }
</script>

<style scoped>

</style>