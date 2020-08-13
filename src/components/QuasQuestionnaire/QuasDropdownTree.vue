<template>
    <div :key="update_key">
        <ul v-for="(item, index) in content" :key="index">
            <li :class="getClassByLevel(content[index].level)" @blur.capture="erase(index)"
                @keyup.ctrl.enter="insert(index)"
                @keyup.ctrl.left="decreaseLevel(index)"
                @keyup.ctrl.right="increaseLevel(index)">
                <quas-text-box v-model="content[index].label" :nullable="true"/>
            </li>
        </ul>
    </div>
</template>

<script>
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    export default {
        name: "QuasDropdownTree",
        components: {QuasTextBox},
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
                return "tree-node-level-" + level.toString();
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
    li {
        list-style: none;
    }

    .tree-node-level-0 {
        margin-left: 0.3rem;
    }

    .tree-node-level-1 {
        margin-left: 0.8rem;
    }

    .tree-node-level-2 {
        margin-left: 1.3rem;
    }

    .tree-node-level-3 {
        margin-left: 1.8rem;
    }

    .tree-node-level-4 {
        margin-left: 2.3rem;
    }

    .tree-node-level-5 {
        margin-left: 2.8rem;
    }
</style>