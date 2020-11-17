import React from "react";

export default class Profile extends React.Component {
    render() {
        return <div className={"profile-pic"}>
            <img src={'http://localhost:8080/dev/img'}></img>
        </div>;
    }
}
