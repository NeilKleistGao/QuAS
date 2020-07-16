<template>
    <div :key="update_key">
        <ul v-for="(item, index) in content" :key="index">
            <li :class="getClassByLevel(content[index].level)">
                <input v-model="content[index].label" class="quas-design-text" type="text"
                        @blur.capture="erase(index)"
                        @keyup.ctrl.enter="insert(index)"
                        @keyup.ctrl.left="decreaseLevel(index)"
                        @keyup.ctrl.right="increaseLevel(index)">
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "QuasDropdownTree",
        props: {
            /**
             * 下拉选项的所有选项列表
             */
            value: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                content: this.value,
                update_key: 0
            };
        },
        watch: {
            content: {
                handler(new_value){
                    /**
                     * 更新选项内容
                     * @event{oninput}
                     */
                    this.$emit("input", new_value);
                },
                deep: true
            }
        },
        methods: {
            /**
             * 根据当前选项所属题目层级调整水平位置
             * @param level 所属题目层级
             * @returns {string} class名称
             * @private
             */
            getClassByLevel(level) {
                return "quas-tree-node-level-" + level.toString();
            },
            /**
             * 删除选项
             * @param index 选项下标
             * @private
             */
            erase(index) {
                if (this.content[index].label === "") {
                    this.content.splice(index, 1);
                    this.update_key++;
                }
            },
            /**
             * 插入相同层级的选项
             * @param index 当前选项的下标
             * @private
             */
            insert(index) {
                this.content.splice(index + 1, 0, {
                    label: "new item",
                    level: this.content[index].level
                });
            },
            /**
             * 增加当前选项的层级，即移动到下一层级的题目中，最大为5
             * @param index 当前选项的下标
             * @private
             */
            increaseLevel(index) {
                if (this.content[index].level < 5 && index != 0 && this.content[index].level <= this.content[index - 1].level) {
                    this.content[index].level++;
                }
                this.update_key++;
            },
            /**
             * 减少当前选项的层级，即移动到上一层级的题目中，最小为0
             * @param index 当前选项的下标
             * @private
             */
            decreaseLevel(index) {
                if (this.content[index].level > 0) {
                    this.content[index].level--;
                }
                this.update_key++;
            }
        }
    }
</script>

<style scoped>

</style>