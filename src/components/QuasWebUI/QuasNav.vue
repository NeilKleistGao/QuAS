<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Questionnaire Analysis System</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navc" aria-controls="navc" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navc">
            <ul class="navbar-nav mr-auto">
                <li v-for="(label, index) in labels" :key="index" class="nav-item">
                    <a class="nav-link" :href="links[index]">{{label}}</a>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "QuasNav",
        props: {
            /**
             * 导航栏的标签数组
             */
            labels: {
                type: Array,
                default: () => {return [];}
            },
            /**
             * 导航栏链接数组
             */
            links: {
                type: Array,
                default: () => {return [];}
            }
        }
    }
</script>

<style scoped>

</style>