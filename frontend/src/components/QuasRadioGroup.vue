<template>
    <div>
        <div v-if="!editable">
            <quas-radio class="quas-list-item"
                        v-for="label in items.labels"
                        :name="name" :text="label"
                        :key="label"
                        v-model="result"/>
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
                        @dragend.native="dragEnd"/>
            <button @click="push" class="quas-operation-button">添加</button>
        </div>
    </div>
</template>

<script>
    import QuasRadio from "@/components/QuasRadio";
    export default {
        name: "QuasRadioGroup",
        components: {QuasRadio},
        props: {
            value: {
                type: Object,
                require: true
            },
            editable: {
                type: Boolean,
                default: false
            },
            items: {
                type: Object
            },
            name: {
                type: String,
                require: true
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                update_key: 0,
                drag_new_index: 0,
                drag_old_index: 0
            };
        },
        methods: {
            erase(index) {
                if (this.labels[index] === "") {
                    this.labels.splice(index, 1);
                    this.update_key++;
                }
            },
            push() {
                this.labels.push("new item");
                this.update_key++;
            },
            dragStart(index) {
                this.drag_old_index = index;
            },
            dragEnter(index) {
                this.drag_new_index = index;
            },
            dragEnd() {
                let temp = this.labels[this.drag_old_index];
                this.labels.splice(this.drag_old_index, 1);
                this.labels.splice(this.drag_new_index, 0, temp);
                this.update_key++;
            }
        },
        watch: {
            result(new_value) {
                this.$emit("input", {
                    result: new_value
                });
            },
            labels(new_value) {
                this.$emit("input", {
                    labels: new_value
                })
            }
        }
    }
</script>

<style scoped>

</style>