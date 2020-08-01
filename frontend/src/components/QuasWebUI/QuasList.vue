<template>
    <div>
        <h3>{{title}}</h3>

        <ul :key="update_key">
            <li v-for="(item, index) in list_items" :key="item"
                @click="setActive(index)"
                @mouseover.capture="switchRemoveIcon(index)">
                <div v-if="active_list[index]" style="width: 100%;" class="quas-list-item-active">
                    <span>{{item}}</span>
                    <i v-if="remove_display[index]" class="iconfont icon-guanbi quas-remove-icon"></i>
                </div>
                <div v-else style="width: 100%;" class="quas-list-item-inactive">
                    <span class="quas-list-item-inactive">{{item}}</span>
                    <i v-if="remove_display[index]" class="iconfont icon-guanbi quas-remove-icon"></i>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "QuasList",
        props: {
            title: {
                type: String,
                default: ""
            },
            items: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                list_items: this.items,
                active_list: [],
                remove_display: [],
                update_key: 0
            };
        },
        methods: {
            setActive(index) {
                for (let i = 0; i < this.active_list.length; i++) {
                    this.active_list[i] = (i === index);
                }

               this.update_key++;
            },
            switchRemoveIcon(index) {
                for (let i = 0; i < this.active_list.length; i++) {
                    this.remove_display[i] = (i === index);
                }
                this.update_key++;
            }
        },
        beforeMount() {
            // eslint-disable-next-line no-unused-vars
            for (let i in this.items) {
                this.active_list.push(false);
                this.remove_display.push(false);
            }
        }
    }
</script>

<style scoped>
    h3 {
        text-align: center;
        margin-top: 10px;
    }

    ul, li {
        margin: 0;
        padding: 0;
    }

    ul {
        overflow-y: scroll;
    }

    ul::-webkit-scrollbar {
        display: none;
    }

    li {
        margin-bottom: 10px;
    }

    span {
        width: 100%;
        margin-left: 10px;
    }
</style>