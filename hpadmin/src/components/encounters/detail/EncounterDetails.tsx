import React, { useEffect, useState } from 'react'
import { connect, RootStateOrAny } from 'react-redux'
import { Encounter } from '../../../services/openAPIClient'
import { getAllAction } from '../../../app/features/encounter/actions'
import { RouteComponentProps } from 'react-router-dom'
import { EncounterOverview } from './bricks/EncounterOverview'
import { Grid } from '@material-ui/core'
import { StatDetails } from './bricks/StatDetails'

interface Props{
  item: Encounter
  loadData: () => void
}

interface MatchParams{
  name: string
}

export enum DetailType {
  STAT = 'stat',
  NONE = 'none'
}

const mapStateToProps = (state: RootStateOrAny, props: RouteComponentProps<MatchParams>) => ({
  item: state.encounterReducer.encounters.find((item: Encounter) => item.name === props.match.params.name)
})

const mapDispatchToProps = (dispatch: any) => ({ 
  loadData: () => dispatch(getAllAction())
})

export const EncounterDetails = (props: Props) => {
  const [activeDetailData , setActiveDetailData] = useState<any>({})
  const [activeDetailType, setActiveDetailType] = useState(DetailType.NONE)
  const onDetailClicked = (data: any, type: DetailType) => {
    setActiveDetailData(data)
    setActiveDetailType(type)
  }

  useEffect(() => {
    props.loadData()
  }, [])
  
  return typeof(props.item) === 'undefined' ? null : (
    <Grid container justify="center">
      <Grid key="overview" item>
        <EncounterOverview encounter={props.item} detailSelector={onDetailClicked}/>
      </Grid>
      <Grid key="detail" item>
       {activeDetailType === DetailType.STAT && <StatDetails stat={activeDetailData}/>}
      </Grid>
    </Grid>
  )
}

export default connect(mapStateToProps, mapDispatchToProps)(EncounterDetails)