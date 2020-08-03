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
            title: {
                type: String,
                required: true
            },
            value: {
                type: String,
                required: true
            },
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
            operate(type) {
                console.log(type)
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