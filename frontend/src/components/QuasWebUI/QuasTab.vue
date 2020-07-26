<template>
    <div>
        <button style="margin-left:5%; visibility: hidden"/>
        <button v-for="(label, index) in labels" :key="index" :class="getButtonStyle(index)" @click="select(index)">{{label}}</button>
        <div class="quas-tab-content" style="display: block">
            <br/>
            <div v-for="(label, index) in labels" :key="index">
                <!--每一个label对应显示的内容-->
                <slot :name="label" v-if="current === index"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasTab",
        props: {
            labels: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                current: 0
            };
        },
        methods: {
            /**
             * 根据选中情况返回按钮对应的class
             * @param index tab按钮的下标
             * @returns {string} class名
             * @private
             */
            getButtonStyle(index) {
                if (index === this.current) {
                    return "quas-tab-button-selected";
                }
                else {
                    return "quas-tab-button";
                }
            },
            /**
             * 按钮点击响应，切换当前页面
             * @param index 标签按钮的下标
             * @private
             */
            select(index) {
                this.current = index;
            }
        }
    }
</script>

<style scoped>

</style>