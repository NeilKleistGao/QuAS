<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <div class="quas-fill-container">
            <h1 class="quas-fill-header">{{title}}</h1>
            <br/>

            <div v-for="(item, index) in questionnaire" :key="index">
                <div style="margin-left: 20px">
                    <h4 style="margin-left: 40px">第{{index + 1}}题</h4>
                    <quas-questionnaire-item style="margin-left: 80px" class="quas-ques-item" :type="item.type" :questionnaire="item.data" :editable="false" v-model="results[index]"/>
                    <br/>
                </div>
            </div>
        </div>
        <br/>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasQuestionnaireItem from "@/components/QuasQuestionnaire/QuasQuestionnaireItem";
    export default {
        name: "FillLayout",
        components: {QuasQuestionnaireItem, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154"],
                title: "关于问卷测试的问卷调查",
                questionnaire: [
                    {
                        type: "none",
                        data: {
                            information: "这是一个测试",
                            content: null
                        }
                    },
                    {
                        type: "radio",
                        data: {
                            information: "这是一个单选题",
                            content: {
                                labels: ["选项１", "选项2", "选项3"]
                            }
                        }
                    },
                    {
                        type: "check",
                        data: {
                            information: "这是一个多选题",
                            content: {
                                labels: ["选项１", "选项2", "选项3"]
                            }
                        }
                    },
                    {
                        type: "date",
                        data: {
                            information: "这是一个日期",
                            content: {
                                type: "date"
                            }
                        }
                    },
                    {
                        type: "text",
                        data: {
                            information: "这是一个填空题",
                            content: {
                            }
                        }
                    },
                    {
                        type: "rich",
                        data: {
                            information: "这是一个多行文本",
                            content: {

                            }
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
                                    label: "选项"
                                }]
                            }
                        }
                    },
                    {
                        type: "sort",
                        data: {
                            information: "这是一个排序题",
                            content: {
                                labels: ["选项1", "选项2", "选项3"]
                            }
                        }
                    }
                ],
                results: [
                    {
                    },
                    {
                        result: ""
                    },
                    {
                        result: []
                    },
                    {
                        result: ""
                    },
                    {
                        result: ""
                    },
                    {
                        result: ""
                    },
                    {
                        result: []
                    },
                    {
                        result: ["选项1", "选项2", "选项3"]
                    }]
            };
        },
        watch: {
        }
    }
</script>

<style scoped>

</style>