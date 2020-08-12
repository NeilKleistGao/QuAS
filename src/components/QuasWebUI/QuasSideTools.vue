<template>
    <div class="card bg-primary col-1">
        <div class="card-header text-center">
            <h4 class="text-light">工具栏</h4>
        </div>
        <div class="card-body row justify-content-center">
            <div v-for="(item, index) in icons" :key="index" style="display: inline-block">
                <img class="btn btn-sm" :src="item + '.png'"  @click="callback(item)">
                <span class="quas-tool-tip">{{tips[index]}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasSideTools",
        data() {
            return {
                icons: ["none", "radio", "check", "text", "rich", "drop", "date", "sort"],
                tips: ["仅文本", "单选题", "多选题", "填空题", "富文本", "下拉菜单", "日期题", "排序"]
            };
        },
        methods: {
            /**
             * 选项点中时的回调函数
             * @param label 选中项的标签
             */
            callback(label) {
                /**
                 * 返回点击项的名称
                 */
                this.$emit("callback", label);
            }
        }
    }
</script>

<style scoped>
    span {
        visibility: hidden;
        width: 80px;
        background-color: #EFEFEF;
        opacity: 75%;
        text-align: center;
        color: black;
        border-radius: 5px;
        padding: 3px 0;
        margin-left: 10px;
        position: absolute;
        z-index: 999;
    }

    img:hover + span {
        visibility: visible;
        z-index: 999;
    }
</style>