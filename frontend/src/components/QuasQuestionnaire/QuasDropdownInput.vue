<template>
    <div>
        <i class="iconfont icon-xiajiantou"></i>
        <div class="quas-dropdown">
            <div @blur.capture="fold(false)">
                <button v-if="result === ''" @click="fold">{{placeholder}}</button>
                <button v-else @click="fold">{{result}}</button>
            </div>
            <div class="quas-dropdown-content" :style="{display: content_display}">
                <a v-for="item in items" :key="item" @click="select(item)">{{item}}</a>
            </div>
        </div>
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
             * 默认的占位符
             */
            placeholder: {
                type: String,
                default: "..."
            },
            /**
             * 备选选项列表
             */
            items: {
                type: Array,
                required: true
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
            /**
             * 更新所选选项
             * @param new_value 新的选项值
             * @private
             */
            select(new_value) {
                this.result = new_value;
                this.fold(false);
            },
            /**
             * 更新下拉框的折叠状态
             * @param inv true表示展开下拉框，false表示将其折叠
             * @private
             */
            fold(inv = true) {
                if (inv) {
                    this.visible = !this.visible;

                    if (this.visible) {
                        this.content_display = "block";
                    }
                    else {
                        this.content_display = "none";
                    }
                }
                else {
                    this.visible = false;
                    this.content_display = "none";
                }
            }
        },
        watch: {
            result(new_value) {
                /**
                 * 更新选项内容
                 * @event{oninput}
                 */
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>
    i {
        margin-left: 0.3rem;
        margin-right: 0.5rem;
    }
</style>