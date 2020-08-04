<template>
    <div>
        <div v-if="!editable">
            <quas-radio class="quas-list-item"
                        v-for="label in items.labels"
                        :name="name" :label="label"
                        :key="label"
                        v-model="result"
                        style="display: inline-block"/>
        </div>
        <div v-else :key="update_key">
            <quas-radio class="quas-list-item"
                        :editable="true"
                        :draggable="true"
                        v-for="(label, index) in labels"
                        :key="index"
                        v-model="labels[index]"
                        @blur.native.capture="erase(index)"
                        @dragstart.native="dragStart(index)"
                        @dragenter.native="dragEnter(index)"
                        @dragend.native="dragEnd"
                        :name="name"/>
            <button @click="push" class="quas-operation-button">添加</button>
        </div>
    </div>
</template>

<script>
    import QuasRadio from "@/components/QuasQuestionnaire/QuasRadio";
    export default {
        name: "QuasRadioGroup",
        components: {QuasRadio},
        props: {
            /**
             * 非可编辑状态下为包含results（结果）对象；可编辑状态下为包含labels数组（提示标签）的对象
             */
            value: {
                type: Object,
                required: true
            },
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 非可编辑状态下替代value值提供labels数组的对象
             */
            items: {
                type: Object
            },
            /**
             * 用于标识单选框组的文本
             */
            name: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                next: this.value.next,
                update_key: 0,
                drag_new_index: 0,
                drag_old_index: 0
            };
        },
        methods: {
            /**
             * 可编辑状态下删除一个选项
             * @param index 选项下标
             * @private
             */
            erase(index) {
                if (this.labels[index] === "") {
                    this.next.splice(index, 1);
                    this.labels.splice(index, 1);
                    this.update_key++;
                }
            },
            /**
             * 可编辑状态下添加一个选项
             * @private
             */
            push() {
                this.next.push(0);
                this.labels.push("new item");
                this.update_key++;
            },
            /**
             * 可编辑状态下开始拖动
             * @param index 拖动的选项的下标
             * @private
             */
            dragStart(index) {
                this.drag_old_index = index;
            },
            /**
             * 可编辑状态下拖动更新
             * @param index 拖动选项的当前下标
             * @private
             */
            dragEnter(index) {
                this.drag_new_index = index;
            },
            /**
             * 结束拖动，更新列表
             * @private
             */
            dragEnd() {
                let temp = this.labels[this.drag_old_index];
                this.labels.splice(this.drag_old_index, 1);
                this.labels.splice(this.drag_new_index, 0, temp);
                this.update_key++;
            }
        },
        watch: {
            result(new_value) {
                /**
                 * 更新选中结果
                 * @event{oninput}
                 */
                this.$emit("input", {
                    result: new_value
                });
            },
            labels(new_value) {
                /**
                 * 更新标签文本
                 * @event{oninput}
                 */
                this.$emit("input", {
                    labels: new_value,
                    next: this.next
                })
            }
        },
        beforeMount() {
            console.log(typeof(this.result));
        }
    }
</script>

<style scoped>

</style>