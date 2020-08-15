<template>
    <div>
        <ul class="nav nav-tabs">
            <li class="nav-item" v-for="(label, index) in labels" :key="index">
                <a :class="getButtonStyle(index)" @click="select(index)">{{label}}</a>
            </li>
        </ul>
        <div class="tab-content">
            <div v-for="(label, index) in labels" :key="label">
                <slot :name="label" v-if="index === current"></slot>
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
                    return "nav-link active";
                }
                else {
                    return "nav-link";
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