<?php

namespace App\Controller;


use App\Entity\CandidatStage;
use App\Form\CandidatStageType;
use App\Repository\CandidatStageRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Mailer\Transport;
use Symfony\Component\Mailer\Mailer;
use Symfony\Component\Mime\Email;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
// Include PhpSpreadsheet required namespaces
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
/**
 * @Route("/candidat/stage")
 */
class CandidatStageController extends AbstractController
{
    /**
     * @Route("/", name="candidat_stage_index", methods={"GET"})
     */
    public function index(CandidatStageRepository $candidatStageRepository): Response
    {
        return $this->render('candidat_stage/index.html.twig', [
            'candidat_stages' => $candidatStageRepository->findAll(),
        ]);
    }
     /**
        * @Route("/excel", name="excel", methods={"GET"})
        */
  public function excel()
  {
      $spreadsheet = new Spreadsheet();
      
      /* @var $sheet \PhpOffice\PhpSpreadsheet\Writer\Xlsx\Worksheet */
      // on définie les en têtes de nos enregistrements
      $sheet = $spreadsheet->getActiveSheet();
      $sheet->setCellValue('A1','ID')
      ->setCellValue('B1','ID entreprise')
      ->setCellValue('C1','ID stagiaire')
      ->setCellValue('D1','Email')
      ->setCellValue('E1','Date candidature')
      ;
      // on récupère toutes les personnes ou on fait une fonction personnalisée à la place du findAll
      $em = $this->getdoctrine()->getManager();
      $candidatStage = $em->getRepository(CandidatStage :: class)->findAll();
      // on place le curseur dans la 2ème position pour ne pas écraser les entêtes ...<br>        
      $aux = 2;
      foreach ($candidatStage as $row)
      // donc pour chaque personne trouvée dans la base de données il met les valeurs au dessous des entêtes
               {
         $sheet->setSheetState(0)
             ->setCellValue('A'.$aux, $row->getId())
             ->setCellValue('B'.$aux, $row->getIdEntreprise())
             ->setCellValue('C'.$aux, $row->getIdStagiaire())
             ->setCellValue('D'.$aux, $row->getEmail())
             ->setCellValue('E'.$aux, $row->getDateCandidature()) 
            ;
      //aux au début était 2 lorsqu'on écrit on l'incrémente pour ne pas écraser à chaque fois
            $aux++;
         };
      $sheet->setTitle("Candidature stage");
      
      // Create your Office 2007 Excel (XLSX Format)
      $writer = new Xlsx($spreadsheet);
      
      // Create a Temporary file in the system
      $fileName = 'Candidature stage.xlsx';
      $temp_file = tempnam(sys_get_temp_dir(), $fileName);
      
      // Create the excel file in the tmp directory of the system
      $writer->save($temp_file);
      
      // Return the excel file as an attachment
      return $this->file($temp_file, $fileName, ResponseHeaderBag::DISPOSITION_INLINE);
  }



    /**
     * @Route("/new", name="candidat_stage_new", methods={"GET","POST"})
     */
    public function new(Request $request, \Swift_Mailer $mailer): Response
    {
        $candidatStage = new CandidatStage();
        $form = $this->createForm(CandidatStageType::class, $candidatStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($candidatStage);
            $entityManager->flush();
            $message = (new \Swift_Message('Hello Email'))
                ->setSubject('Candidature de stage ')
                ->setFrom('jobbridge938@gmail.com')
                ->setTo($candidatStage->getEmail())
                ->setBody('Candidature de stage ajoutée ');
                $mailer->send($message);
            return $this->redirectToRoute('candidat_stage_index');
        }

        return $this->render('candidat_stage/new.html.twig', [
            'candidat_stage' => $candidatStage,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/calendar", name="candidat_stage_callendar")
     */
    public function calendar(CandidatStageRepository $calendar)
    {
        $events = $calendar->findAll();

        $rdvs = [];

        foreach($events as $event){
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getDateCandidature()->format('Y-m-d H:i:s'),
                'end' => $event->getDateCandidature()->format('Y-m-d H:i:s'),
                'title' => $event->getEmail(),

            ];
        }

        $data = json_encode($rdvs);

        return $this->render('candidat_stage/calendar.html.twig', compact('data'));
    }

    /**
     * @Route("/news", name="offre_stage_news")
     */
    public function tri(CandidatStageRepository $offreStageRepository)
    {
        $student=$this->getDoctrine()->getRepository(CandidatStage::class)->findByASC();
        return $this->render('candidat_stage/index.html.twig', [
            'candidat_stages' => $student,
        ]);
    }
    


    /**
     * @Route("/{id}", name="candidat_stage_show", methods={"GET"})
     */
    public function show(CandidatStage $candidatStage): Response
    {
        return $this->render('candidat_stage/show.html.twig', [
            'candidat_stage' => $candidatStage,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="candidat_stage_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, CandidatStage $candidatStage): Response
    {
        $form = $this->createForm(CandidatStageType::class, $candidatStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('candidat_stage_index');
        }

        return $this->render('candidat_stage/edit.html.twig', [
            'candidat_stage' => $candidatStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidat_stage_delete", methods={"DELETE"})
     */
    public function delete(Request $request, CandidatStage $candidatStage): Response
    {
        if ($this->isCsrfTokenValid('delete'.$candidatStage->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($candidatStage);
            $entityManager->flush();
        }

        return $this->redirectToRoute('candidat_stage_index');
    }
   

}
