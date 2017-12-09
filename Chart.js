/**
 * Created by alexandraneamtu on 09/12/2017.
 */
import React from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    processColor,
} from 'react-native';

import {StackNavigator, SafeAreaView} from 'react-navigation';

import {PieChart} from 'react-native-charts-wrapper';

export class Chart extends React.Component {

    constructor() {
        super();

        this.state = {
            capitalLetters: 0,
            lowercaseLetters: 0,
            spaces: 0,
            legend: {
                enabled: true,
                textSize: 8,
                form: 'CIRCLE',
                position: 'RIGHT_OF_CHART',
                wordWrapEnabled: true
            },
            description: {
                text: 'Pie chart description',
                textSize: 15,
                textColor: processColor('darkgray'),

            }
        };
    }


    analise(title) {
        for (i = 0; i < title.length; i++) {
            if (title[i] === title[i].toUpperCase())
                this.state.capitalLetters++;
            if (title[i] === title[i].toLowerCase())
                this.state.lowercaseLetters++;
            if (title[i] === " ")
                this.state.spaces++;
        }
    }

    render() {
        const {params} = this.props.navigation.state;
        var title = params ? params.title : "<undefined>";
        this.analise(title);
        return (
            <SafeAreaView style={{flex: 1}}>
                <View style={styles.container}>
                    <PieChart
                        style={styles.chart}
                        logEnabled={true}
                        chartDescription={this.state.description}
                        data={{
                            dataSets: [{
                            values: [
                                {value: this.state.capitalLetters, label: 'UpperCaseLetters'},
                                {value: this.state.lowercaseLetters, label: 'LowerCaseLetters'},
                                {value: this.state.spaces, label: 'spaces'}],
                            label: 'Legend',
                            config: {
                            colors: [processColor('#C0FF8C'), processColor('#FFF78C'), processColor('#FFD08C')],
                            valueTextSize: 20,
                            valueTextColor: processColor('green'),
                            sliceSpace: 5,
                            selectionShift: 13
                        }
                        }]}}
                        legend={this.state.legend}

                        entryLabelColor={processColor('black')}
                        entryLabelTextSize={20}


                        rotationEnabled={true}
                        rotationAngle={45}
                        drawSliceText={true}
                        usePercentValues={true}
                        centerTextRadiusPercent={100}
                        holeRadius={40}
                        holeColor={processColor('#f0f0f0')}
                        transparentCircleRadius={45}
                        transparentCircleColor={processColor('#f0f0f088')}
                        maxAngle={360}
                    />
                </View>

            </SafeAreaView>

        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    chart: {
        flex: 1
    }
});

