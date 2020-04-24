<template>
    <div>
        <div v-if="!editable">
            <quas-check-box class="quas-list-item"
                            v-for="(label, index) in items.labels"
                            :key="label"
                            :text="label"
                            v-model="result"
                            :selectable="selectable_list[index]"/>
        </div>
        <div v-else :key="update_key">
            <quas-check-box class="quas-list-item"
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
    import QuasCheckBox from "@/components/QuasCheckBox";

    export default {
        name: "QuasCheckBoxGroup",
        components: {QuasCheckBox},
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
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                update_key: 0,
                drag_new_index: 0,
                drag_old_index: 0,
                selectable_list: []
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
                if (new_value.length === this.items.max && this.items.max !== -1) {
                    for (let i = 0; i < this.selectable_list.length; i++) {
                        if (new_value.indexOf(this.items.labels[i]) === -1) {
                            this.selectable_list[i] = false;
                        }
                    }
                }
                else {
                    for (let i = 0; i < this.selectable_list.length; i++) {
                        this.selectable_list[i] = true;
                    }
                }

                this.$emit("input", {
                    result: new_value
                });
            },
            labels(new_value) {
                this.$emit("input", {
                    labels: new_value,
                    min: this.value.min,
                    max: this.value.max
                })
            }
        },
        beforeMount() {
            // eslint-disable-next-line no-prototype-builtins
            if (this.items != undefined){
                for (let i in this.items.labels) {
                    this.selectable_list.push(true);
                    this.selectable_list[i] = true;
                }
            }
        }
    }
</script>

<style scoped>

</style>