<template>
    <div class="form-group">
        <select class="custom-select" v-model="result">
            <option v-for="item in items" :key="item" :value="item">{{item}}</option>
        </select>
    </div>
</template>

<script>
    export default {
        name: "QuasDropdownInput",
        props: {
            /**
             * 下拉选项的选择结果
             */
            value: {
                type: String,
                required: true
            },
            /**
             * 备选选项列表
             */
            items: {
                type: Array,
                required: true
            },
            /**
             * 是否隐藏默认图标
             */
            hidden_icon: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                result: this.value,
                visible: false,
                content_display: "none"
            };
        },
        methods: {
        },
        watch: {
            result(new_value) {
                /**
                 * 更新选项内容
                 * @event{oninput}
                 */
                this.$emit("input", new_value);
                console.log(new_value);
            }
        }
    }
</script>

<style scoped>
    i {
        margin-left: 0.3rem;
        margin-right: 0.5rem;
    }

    select {
        overflow: hidden;
    }
</style>