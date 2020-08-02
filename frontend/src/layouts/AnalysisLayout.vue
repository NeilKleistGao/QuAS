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
                    <label style="display: inline-block">类型：</label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasList from "@/components/QuasWebUI/QuasList";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    export default {
        name: "AnalysisLayout",
        components: {QuasTextBox, QuasList, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试", "问卷分析测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154", "#/questionnaire/analysis/3154"],
                title: "关于问卷测试的问卷调查",
                analyze_title: [],
                analyze_items: [{
                    title: "分析1",
                    type: "",
                    data: {

                    }
                },{
                    title: "分析2",
                    type: "",
                    data: {

                    }
                },{
                    title: "分析3",
                    type: "",
                    data: {

                    }
                }],
                active_index: -1,
                update_key: 0
            }
        },
        beforeMount() {
            for (let item of this.analyze_items) {
                this.analyze_title.push(item.title);
            }
        },
        methods: {
            update(index) {
                this.active_index = index;
                this.update_key++;
            }
        }
    }
</script>

<style scoped>

</style>