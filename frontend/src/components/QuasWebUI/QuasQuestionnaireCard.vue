<template>
    <div class="quas-questionnaire-card">
        <div class="quas-card-operation-grid">
            <div class="quas-card-operation-button">
                <img src="/edit.png" @click="operate('edit')"/>
                <small>编辑问卷</small>
            </div>

            <div v-if="state === 'idle'" class="quas-card-operation-button">
                <img src="/start.png" @click="operate('switch')"/>
                <small>开始发放</small>
            </div>
            <div v-else class="quas-card-operation-button">
                <img src="/stop.png" @click="operate('switch')"/>
                <small>停止发放</small>
            </div>

            <div class="quas-card-operation-button">
                <img src="/statistics.png" @click="operate('statistics')"/>
                <small>问卷统计</small>
            </div>

            <div class="quas-card-operation-button">
                <img src="/analysis.png" @click="operate('analyze')"/>
                <small>数据分析</small>
            </div>
        </div>
        <div style="clear: both"/>
        <img src="/questionnaire.png">
        <label>{{title}}</label>
    </div>
</template>

<script>
    export default {
        name: "QuasQuestionnaireCard",
        props: {
            /**
             * 问卷标题
             */
            title: {
                type: String,
                required: true
            },
            /**
             * 问卷当前状态，只有"idle"（未开始发放）和"running"（正在发放）两种情况
             */
            value: {
                type: String,
                required: true
            },
            /**
             * 问卷的id
             */
            qid: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                state: this.value
            };
        },
        methods: {
            /**
             * 点击操作图标后的回调函数
             * @param type 所点击的图标类型
             */
            operate(type) {
                if (type === "edit") {
                    window.location = "#/questionnaire/design/" + this.qid;
                }
                else if (type === "switch") {
                    if (this.state === "idle") {
                        this.state = "running";
                    }
                    else {
                        this.state = "idle";
                    }

                    /**
                     * 更新问卷的状态
                     */
                    this.$emit("input", this.state);
                }
                else if (type === "statistics") {
                    window.location = "#/questionnaire/result/" + this.qid;
                }
                else if (type === "analyze") {
                    window.location = "#/questionnaire/analysis/" + this.qid;
                }
            }
        }
    }
</script>

<style scoped>

</style>