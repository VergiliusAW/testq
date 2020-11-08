import React from 'react'
import Article from './Article'
import './ArticleList.css'

class ArticlesList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [
                {
                    "id": 1,
                    "title": "TTT",
                    "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris et metus nisl. Etiam nibh nibh, blandit ut ultrices vitae, dictum vitae nulla. Aenean viverra sapien at lacus feugiat, non mattis ex interdum. Sed sollicitudin erat vel mauris dapibus, quis suscipit sem maximus. Nam maximus libero ut orci aliquam, eget eleifend turpis elementum. Proin sit amet hendrerit urna, at consectetur nisi. Nullam sed sapien eget velit molestie gravida fringilla id velit. Aliquam varius diam urna, vitae tempus turpis ornare id. Fusce in sodales arcu. Pellentesque mattis urna ut dolor gravida commodo. Sed massa risus, maximus in libero eu, dapibus laoreet nisl. Cras mattis justo eget nisl bibendum, mattis accumsan ante posuere. Fusce nisi mauris, convallis pellentesque augue eu, cursus venenatis tortor."
                },
                {
                    "id": 2,
                    "title": "TTT",
                    "body": "Ut euismod vulputate felis, vel sagittis tortor facilisis sed. Duis quis nulla non dui efficitur mollis et a est. Quisque facilisis nunc quam, ut posuere lacus fringilla vitae. Phasellus vehicula scelerisque augue ut eleifend. Quisque porta turpis ante. Praesent pulvinar interdum leo, sed varius leo. Phasellus a laoreet magna. Aenean dolor est, dignissim a velit a, convallis blandit leo. Curabitur interdum cursus augue, a faucibus purus fringilla id. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut vitae ligula augue. Donec fermentum facilisis urna nec fringilla. Proin urna enim, aliquet iaculis maximus eget, vehicula interdum quam. Nunc sed rutrum odio. Nullam quis velit neque."
                }
            ]
        };
    }

    componentWillMount() {
        fetch('api/posts').then((response) => response.json())
            .then((responseJson) => {
                this.setState({ data : responseJson })
            })
            .catch((error) => {
                console.error(error);
            });
    }

    render() {
        return (
            <div className={"grid"}>
                {this.state.data.map((article) => (
                        <Article key={article.id} data={article}/>
                    )
                )}
            </div>
        )
    }
}

export default ArticlesList