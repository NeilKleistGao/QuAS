<template>
    <div>
        <quas-nav/>

        <h2 style="margin-left:20px">我的问卷</h2>

        <div v-for="(sub_list, index) in list" :key="index" class="quas-table-grid">
            <div v-for="item in sub_list" :key="item.title">
                <quas-questionnaire-card :title="item.title" :qid="item.qid" v-model="item.state"/>
            </div>
        </div>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasQuestionnaireCard from "@/components/QuasWebUI/QuasQuestionnaireCard";
    export default {
        name: "CollectionLayout",
        components: {QuasQuestionnaireCard, QuasNav},
        data() {
            return {
                list: [[{
                    title: "关于问卷测试的问卷调查",
                    state: "idle",
                    qid: "3154"
                }]]
            };
        }
    }
</script>

<style scoped>

</style>