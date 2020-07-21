<template>
    <div>
        <div v-if="!editable" :key="update_key" class="quas-dropdown-grid">
            <div v-for="(enable, index) in labels_enable" :key="index">
                <div v-if="enable">
                    <label>{{contents.labels[index]}}</label>
                    <quas-dropdown-input v-model="result[index]" :items="getOptions(index)" :index="index" @click.native="modify(index)"/>
                </div>
            </div>
        </div>
        <div v-else>
            <quas-dropdown-tree v-model="items"/>
        </div>
    </div>
</template>

<script>
    import QuasDropdownTree from "@/components/QuasQuestionnaire/QuasDropdownTree";
    import QuasDropdownInput from "@/components/QuasQuestionnaire/QuasDropdownInput";
    export default {
        name: "QuasDropdown",
        components: {QuasDropdownInput, QuasDropdownTree},
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 非可编辑状态下为包含结果的results数组；可编辑状态下为小标题数组labels和选项数组items
             */
            value: {
                type: Object,
                required: true
            },
            /**
             * 非可编辑状态下的题目数据
             */
            contents: {
                type: Object,
                default: null
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                items: this.value.items,
                update_key: 0,
                labels_enable: [true, false, false, false, false, false],
                previous_result: []
            };
        },
        methods: {
            /**
             * 加载当前下拉选项框的备选选项
             * @param index 当前下拉选项框的下标
             * @returns {[]} 备选选项数组
             * @private
             */
            getOptions(index) {
                let res = [];
                let len = this.contents.items.length;

                if (index === 0) {
                    for (let i = 0; i < len; i++) {
                        if (this.contents.items[i].level === 0) {
                            res.push(this.contents.items[i].label);
                        }
                    }
                }
                else {
                    for (let i = 0; i < len; i++) {
                        if (this.contents.items[i].label === this.result[index - 1] && i != len - 1) {
                            if (this.contents.items[i + 1].level > this.contents.items[i].level) {
                                res.push(this.contents.items[i + 1].label);
                                for (let j = i + 2; j < len; j++) {
                                    if (this.contents.items[j].level != this.contents.items[i + 1].level) {
                                        break;
                                    }

                                    res.push(this.contents.items[j].label);
                                }
                            }

                            break;
                        }
                    }
                }

                return res;
            },
            /**
             * 更新当前下拉选框的选项
             * @param index 当前下拉选框的下标
             */
            modify(index) {
                if (this.result[index] != this.previous_result[index]) {
                    this.previous_result[index] = this.result[index];
                    for (let j = index + 1; j < this.result.length; j++) {
                        this.labels_enable[j] = false;
                        this.previous_result[j] = this.result[j] = "";
                    }

                    let item_len = this.contents.items.length;
                    for (let j = 0; j < item_len; j++) {
                        if (this.contents.items[j].label === this.result[index] && j != item_len - 1) {
                            if (this.contents.items[j + 1].level > this.contents.items[j].level) {
                                this.labels_enable[this.contents.items[j + 1].level] = true;
                            }
                            break;
                        }
                    }

                    /**
                     * 更新所选内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        result: this.result
                    });

                    this.update_key++;
                }
            }
        },
        watch: {
            items: {
                handler(new_value) {
                    /**
                     * 可编辑状态下更新选项内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        labels: this.labels,
                        items: new_value
                    });
                },
                deep: true
            }
        },
        beforeMount() {
            if (!this.editable) {
                for (let item of this.result) {
                    this.previous_result.push(item);
                }
            }
        }
    }
</script>

<style scoped>
    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
        margin-right: 0.5rem;
    }
</style>