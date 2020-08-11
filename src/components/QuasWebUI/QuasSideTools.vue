<template>
    <div class="quas-tool-bar">
        <div v-for="(item, index) in icons" :key="index">
            <img class="quas-tool-icon" :src="item + '.png'" width="32" height="32" @click="callback(item)">
            <span class="quas-tool-tip">{{tips[index]}}</span>
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

</style>