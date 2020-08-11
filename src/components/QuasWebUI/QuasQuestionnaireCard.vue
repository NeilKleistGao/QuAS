<template>
    <div :class="getCardClass()" style="width: 22rem; height: 10rem; display: inline-block;" @mouseover="hovered=true" @mouseleave="hovered=false">
        <div class="card-body row justify-content-center text-center">
            <img v-if="!hovered" src="/questionnaire.png" width="64" height="64">
            <div v-else>
                <div style="display: inline-block; margin: 0 1rem; padding: 0">
                    <img src="/edit.png" @click="operate('edit')"/>
                    <small class="text-light">编辑问卷</small>
                </div>

                <div v-if="state === 'idle'" style="display: inline-block; margin: 1rem">
                    <img src="/start.png" @click="operate('switch')"/>
                    <small class="text-light">开始发放</small>
                </div>
                <div v-else style="display: inline-block; margin: 1rem">
                    <img src="/stop.png" @click="operate('switch')"/>
                    <small class="text-light">停止发放</small>
                </div>

                <div style="display: inline-block; margin: 1rem">
                    <img src="/statistics.png" @click="operate('statistics')"/>
                    <small class="text-light">问卷统计</small>
                </div>

                <div style="display: inline-block; margin: 1rem">
                    <img src="/analysis.png" @click="operate('analyze')"/>
                    <small class="text-light">数据分析</small>
                </div>
                <div style="clear: both"/>
            </div>
        </div>
        <div class="card-footer text-center">
            <span v-if="!hovered" class="card-text">{{title}}</span>
            <span v-else class="card-text text-light">{{title}}</span>
        </div>
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
                state: this.value,
                hovered: false
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
            },
            getCardClass() {
                if (this.hovered) {
                    return "card bg-primary";
                }
                else {
                    return "card border-primary";
                }
            }
        }
    }
</script>

<style scoped>
    small {
        display: block;
    }

    .card {
        transition: all 0.3s;
    }

    img {
        cursor: pointer;
    }
</style>