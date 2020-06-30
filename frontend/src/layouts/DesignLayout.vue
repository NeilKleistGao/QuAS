<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links" :active_index="0"/>
        <quas-side-tools @callback="insert"/>

        <div class="quas-design-container">
            <div class="quas-design-header">
                <quas-text-box label="问卷标题："/>
                <br/>
            </div>

            <div v-for="(item, index) in questionnaire" :key="index">
                <h4 style="margin-left: 1rem">第{{index}}题</h4>
                <quas-questionnaire-item class="quas-ques-item" :type="item.type" v-model="item.data" :editable="true"/>
                <br/>
            </div>
        </div>
    </div>
</template>

<script>

    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasSideTools from "@/components/QuasWebUI/QuasSideTools";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    import QuasQuestionnaireItem from "@/components/QuasQuestionnaire/QuasQuestionnaireItem";
    export default {
        name: "DesignLayout",
        components: {QuasQuestionnaireItem, QuasTextBox, QuasSideTools, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154"],
                questionnaire: [
                    {
                        type: "none",
                        data: {
                            information: "开始设计您的问卷",
                            content: null
                        }

                    }
                ]
            };
        },
        methods: {
            insert(label) {
                let new_item = {
                    type: label
                };
                if (label === "radio") {
                    new_item["data"] = {
                        information: "单选题",
                        content: {
                            labels: ["选项"]
                        }
                    };
                }
                else if (label === "check") {
                    new_item["data"] = {
                        information: "多选题",
                        content: {
                            labels: ["选项"],
                            min: 0,
                            max: 1
                        }
                    };
                }
                else if (label === "text") {
                    new_item["data"] = {
                        information: "填空题",
                        content: {
                            type: "text",
                            reg: null
                        }
                    };
                }
                else if (label === "rich") {
                    new_item["data"] = {
                        information: "多行文本",
                        content: {
                            min: 0,
                            max: 999
                        }
                    };
                }
                else if (label === "date") {
                    new_item["data"] = {
                        information: "日期",
                        content: {
                            type: "date",
                            range: false,
                            min: "",
                            max: ""
                        }
                    };
                }
                else if (label === "drop") {
                    new_item["data"] = {
                        information: "下拉菜单",
                        content: {
                            labels: ["标题", "标题", "标题", "标题", "标题", "标题"],
                            items: [{
                                label: "选项",
                                level: 0
                            }]
                        }
                    };
                }
                else if (label === "sort") {
                    new_item["data"] = {
                        information: "排序题",
                        content: {
                            labels: ["选项"]
                        }
                    };
                }
                else if (label === "none") {
                    new_item["data"] = {
                        information: "文本说明",
                        content: null
                    };
                }
                else {
                    return;
                }

                this.questionnaire.push(new_item);
            }
        },
        watch: {
        }
    }
</script>

<style scoped>

</style>